% super nastroj na porovnavani typu numerickych integraci https://demonstrations.wolfram.com/ComparingBasicNumericalIntegrationMethods/
clear;
clc;
% funkce pro obdelnikove pravidlo
% https://www.emathhelp.net/en/calculators/calculus-2/midpoint-rule-calculator/?f=sin%28x%29%2Fx&a=0&b=1&n=10
f = @(x) sin(x) ./ x;

% funkce pro lichobeznikove pravidlo
g = @(x) 2*x + 1;
h = @(x) (1/2 + cos(x)) .* sin(x);

% obdelnikove pravidlo
integralF_value = integral(f, 0, 1);
% jemnosti deleni
resolutions = arrayfun(@(x) 10^x, 1:6);
% obdelnikove pravidlo aplikovane na funkci f s ruznymi jemnostmi deleni
results = arrayfun(@(n) composite_midpoint(f, 0, 1, n), resolutions);
% jak moc jsme se spletli oproti matlabovskymu integral()
diffs = integralF_value - results;
% tabulka ktera to above hezky sesumarizuje
CMR = array2table([resolutions', results', diffs'], "VariableNames",{'Resolution', 'Approximation', 'Error'})

% lichobeznikove pravidlo
% funkce g
integralG_value = integral(g, -1, 1);
[I, k] = adapt_trapz(g, -1, 1, 1000, 1e-12);

% funkce h
integralH_value = integral(h, 0, 1);
[J, m] = adapt_trapz(h, 0, 1, 1000, 1e-4);

% tabulka
ATR = array2table([[k m]', [I J]', ([I J] - [integralG_value integralH_value])'], "VariableNames",{'Resolution', 'Approximation', 'Error'})