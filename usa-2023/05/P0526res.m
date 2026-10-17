clear all
clc

%ad a
%normální rozdìlení s parametry N(0,3)
pravd_a=normcdf(5,0,3)-normcdf(-2,0,3)

%ad b
%binomické rozdìlení
%pravdìpodobnost všechny v intervalu
pravd_b=binopdf(3,3,pravd_a)

%alespoò jeden bude mít chybu mimo interval
pravd_b=1-pravd_b