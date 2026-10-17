clf;
clc;
clear;
% load data
T = readtable('insurance.csv');

% vyfiltruju si smokery a nonsmokery
nonsmokers = T(strcmp(T.smoker, 'no'), :);
smokers = T(strcmp(T.smoker, 'yes'), :);

% regresivni primka zavislosti nakladu na veku
[k_age_smokers, q_age_smokers] = lin_regression(smokers.age, smokers.charges);
[k_age_nonsmokers, q_age_nonsmokers] = lin_regression(nonsmokers.age, nonsmokers.charges);

% regresivni primka zavislosti nakladu na bmi
[k_bmi_smokers, q_bmi_smokers] = lin_regression(smokers.bmi, smokers.charges);
[k_bmi_nonsmokers, q_bmi_nonsmokers] = lin_regression(nonsmokers.bmi, nonsmokers.charges);

% graf naklady/vek
hold on;
xlabel("Věk");
ylabel('Náklady');
legend();

% kuraci
scatter(smokers.age, smokers.charges, 36, "red", "DisplayName", "Kuřáci");
% plotnu primku - limity grafu of min veku do max veku
fplot(@(x) k_age_smokers * x + q_age_smokers, [min(smokers.age) max(smokers.age)], "DisplayName", sprintf("Trend y=%0.3fx %+0.3f", k_age_smokers, q_age_smokers));


% nekuraci
scatter(nonsmokers.age, nonsmokers.charges, 36, "green", "DisplayName", "Nekuřáci");
% plotnu primku - limity grafu of min veku do max veku
fplot(@(x) k_age_nonsmokers * x + q_age_nonsmokers, [min(nonsmokers.age) max(nonsmokers.age)], "DisplayName", sprintf("Trend y=%0.3fx %+0.3f", k_age_nonsmokers, q_age_nonsmokers));

% ulozime graf
saveas(gcf, "graph_age.png");

% clear the figure
hold off;
clf(gcf);

% graf naklady/bmi
hold on;
xlabel("BMI");
ylabel('Náklady');
legend();

scatter(smokers.bmi, smokers.charges, 36, "red", "DisplayName", "Kuřáci");
fplot(@(x) k_bmi_smokers * x + q_bmi_smokers, [min(smokers.bmi) max(smokers.bmi)], "DisplayName", sprintf("Trend y=%0.3fx %+0.3f", k_bmi_smokers, q_bmi_smokers));

scatter(nonsmokers.bmi, nonsmokers.charges, 36, "green", "DisplayName", "Nekuřáci");
fplot(@(x) k_bmi_nonsmokers * x + q_bmi_nonsmokers, [min(nonsmokers.bmi) max(nonsmokers.bmi)], "DisplayName", sprintf("Trend y=%0.3fx %+0.3f", k_bmi_nonsmokers, q_bmi_nonsmokers));

% ulozime graf
saveas(gcf, "graph_bmi.png");
