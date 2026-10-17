%% 1

%% 2
clear all; close all; echo off; clc;

pocetUdalosti = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];
frekvenceUdalosti = [51, 190, 268, 232, 136, 67, 36, 11, 6, 3];

% a)
pocetPozorovani = sum(frekvenceUdalosti);

lambda = sum(pocetUdalosti .* frekvenceUdalosti) / pocetPozorovani;

expectedFrekv = zeros(size(pocetUdalosti));
for k = 1:numel(pocetUdalosti)
    expectedFrekv(k) = pocetPozorovani * poisspdf(pocetUdalosti(k), lambda);
end
expectedFrekv(end) = pocetPozorovani - sum(expectedFrekv(1:end-1));

% Chi-kvadrát test dobré shody
[h, p] = chi2gof(pocetUdalosti, 'Ctrs', pocetUdalosti, 'Frequency', frekvenceUdalosti, 'Expected', expectedFrekv, 'NParams',1);

if h == 0
    disp(['Data pravděpodobně pocházejí z Poissonova rozdělení. p-hodnota = ', num2str(p)])
else
    disp(['Data pravděpodobně nepocházejí z Poissonova rozdělení. p-hodnota = ', num2str(p)])
end

% b)
alpha = 0.05;
n = sum(frekvenceUdalosti);

lambda_hat = sum(pocetUdalosti .* frekvenceUdalosti) / n;
lower_bound = icdf('Poisson', alpha/2, lambda_hat);
upper_bound = icdf('Poisson', 1-alpha/2, lambda_hat);

disp(['95% intervalový odhad parametru lambda: [', num2str(lower_bound), ', ', num2str(upper_bound), ']']);
%% 3
clear all; close all; echo off; clc;

% Statistiky pro rok 2021
n1 = 1214; % Celkový počet dotázaných
x1 = 387; % Počet kladných odpovědí

% Statistiky pro rok 2022
n2 = 1518; % Celkový počet dotázaných
x2 = 531; % Počet kladných odpovědí

% Výpočet podílů
p1 = x1 / n1; % Podíl kladných odpovědí v roce 2021
p2 = x2 / n2; % Podíl kladných odpovědí v roce 2022

% Výpočet intervalů spolehlivosti pomocí binofit
[~, pci1] = binofit(x1, n1); % Interval spolehlivosti pro rok 2021
[~, pci2] = binofit(x2, n2); % Interval spolehlivosti pro rok 2022

% Porovnání intervalů
if pci1(2) < pci2(1)
    disp("Podíl kladných odpovědí v roce 2022 je signifikantně vyšší než v roce 2021.");
else
    disp("Nepodařilo se prokázat signifikantní zvýšení podílu kladných odpovědí v roce 2022.");
end

% Výpis intervalů spolehlivosti
disp("Interval spolehlivosti pro rok 2021:");
disp(pci1);
disp("Interval spolehlivosti pro rok 2022:");
disp(pci2);
%% 4
clear all; close all; echo off; clc;

prumerna_hmotnost = 400; 
smerodatna_odchylka = 150; 
zakoupena_hmotnost = 50 * 1000;

% a)
pocet_lidi = 60;
pozadovana_hmotnost = prumerna_hmotnost * pocet_lidi;
pravdepodobnost_staceni = 1 - normcdf(pozadovana_hmotnost, zakoupena_hmotnost, smerodatna_odchylka);

% b)
pozadovana_pravdepodobnost = 0.25;
potrebna_hmotnost = norminv(pozadovana_pravdepodobnost, zakoupena_hmotnost, smerodatna_odchylka);
pocet_navstevniku = ceil(potrebna_hmotnost / prumerna_hmotnost);

fprintf('a) Pravděpodobnost, že požadované množství masa bude stačit: %.2f%%\n', pravdepodobnost_staceni * 100);
fprintf('b) Počet návštěvníků pro pravděpodobnost 25 %%: %d\n', pocet_navstevniku);
%% 5
clear all; clc; echo off; close all;

X=[1,2,3,4,5,6,7,8,9,10];
Y=[121, 125, 136, 176, 301, 720, 2100, 6800, 22000, 73000];

% a) Proložení dat funkcí Y = e^(ax+b) + c
fun = @(coef, x) exp(coef(1)*x + coef(2)) + coef(3);
start_coefs = [0, 0, 0]; 
coefs = lsqcurvefit(fun, start_coefs, X, Y); % Metoda nejmenších čtverců pro non-linear

fprintf('a) Koeficienty proložení: a = %.4f, b = %.4f, c = %.4f\n', coefs(1), coefs(2), coefs(3));

% b) Testování parametru c na hladině významnosti 5%
params = [coefs(1), coefs(2), coefs(3)];
c_value = 100;
nlm = fitnlm(X, Y, fun, params);
ci = coefCI(nlm, 0.05);

if ci(3, 1) <= 100 && 100 <= ci(3, 2)
    disp('Hodnota 100 leží v 95% konfidenčním intervalu pro C.')
else
    disp('Hodnota 100 neleží v 95% konfidenčním intervalu pro C.')
end

% c)
x = unifrnd(0, 1, 1, 100);
y = x + normrnd(0, 1, 1, 100);

model = fitlm(x, y);

coefficients = model.Coefficients.Estimate;
a = coefficients(2); 
b = coefficients(1); 
rSquared = model.Rsquared.Ordinary;

fprintf('c) Výsledky proložení lineární funkcí:\n');
fprintf('Koeficient Slope(a): %.4f\n', a);
fprintf('Koeficient Intercept(b): %.4f\n', b);
fprintf('R-kvadrát: %.4f\n', rSquared);

if rSquared >= 0.8
    disp('Linearni model y = ax + b je dobry.')
else
    disp('Linearni model y = ax + b neni dobry.')
end