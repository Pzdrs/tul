clear; clc

% (1)
stredniHodnoty = 1:100;
rozptyly = 1:100;
rndNumbers = zeros(1, 100);

for i = 1:100
    rndNumbers(i) = normrnd(stredniHodnoty(i), sqrt(rozptyly(i)));
end

prob = normcdf(5000, sum(stredniHodnoty), sqrt(sum(rozptyly)))

% (2)
probKarty = [4/32 4/31 4/30 4/29 4/28 4/27 4/26 4/25];
prod(probKarty)

probKartyDvaBalicky = [8/64 8/63 8/62 8/61 8/60 8/59 8/58 8/57];
prod(probKartyDvaBalicky)

% (3)
Vek6=[38,41,52,46,54,51,45,41,42,44,46,47,48,46,45,41,42,43,44,41,52,51,54,57,52,49,46,45];
Vek7=[42,45,47,49,51,42,43,43,38,39,42,45,52,57,58];
Vek8=[51,54,28,52,54,48,45,46,46,42,43,45,47,48,51,52,54,21,52,46,45];
Vek9=[57,54,52,45,41,48,44,45,39,42,46,41,43,42,45,47,54,52];

Vek6Mean=mean(Vek6);
Vek6STD=std(Vek6);
Vek7Mean=mean(Vek7);
Vek7STD=std(Vek7);
Vek8Mean=mean(Vek8);
Vek8STD=std(Vek8);
Vek9Mean=mean(Vek9);
Vek9STD=std(Vek9);

Vek6score=(Vek6-Vek6Mean)/Vek6STD;
Vek7score=(Vek7-Vek7Mean)/Vek7STD;
Vek8score=(Vek8-Vek8Mean)/Vek8STD;
Vek9score=(Vek9-Vek9Mean)/Vek9STD;

threshold = 2;

disp("Odlehle hodnoty pro Vek6:");
outliersVek6 = Vek6(Vek6score > threshold | Vek6score < -threshold);
disp(outliersVek6);

disp("Odlehle hodnoty pro Vek7:");
outliersVek7 = Vek7(Vek7score > threshold | Vek7score < -threshold);
disp(outliersVek7);

disp("Odlehle hodnoty pro Vek8:");
outliersVek8 = Vek8(Vek8score > threshold | Vek8score < -threshold);
disp(outliersVek8);

disp("Odlehle hodnoty pro Vek9:");
outliersVek9 = Vek9(Vek9score > threshold | Vek9score < -threshold);
disp(outliersVek9);

alpha=0.05; % default pro ttest
[h, p, ci, stat] = ttest2(Vek6, Vek8);
if p > alpha
    disp('Střední hodnoty Vek6 a Vek8 jsou stejné.')
else
    disp('Střední hodnoty Vek6 a Vek8 nejsou stejné.')
end
% (4)
poruchy2022 = 81;
stroje2022 = 24;
pracovni_dny2022 = 52*5;

% střední hodnota 
lambda2022 = poruchy2022 / (stroje2022 * pracovni_dny2022);

stroje2024_2smeny = 40;
stroje2024_3smeny = 10;
pracovni_dny2024 = (52-4) * 5;  

lambda_2024 = lambda2022 * ((stroje2024_2smeny * 2 + stroje2024_3smeny * 3) * pracovni_dny2024);

% a) mene nez 300 poruch
prob_lt300 = poisscdf(300, lambda_2024)