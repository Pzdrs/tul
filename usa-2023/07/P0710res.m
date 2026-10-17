clear all
clc

%pravdepodobnost uspechu 12%, pocer respondentu = 400
p=0.12;
n=400;

%dosadim do vzorce v kapitole 7.7
p_min=p-norminv(0.975)*sqrt(p*(1-p)/n)
p_max=p+norminv(0.975)*sqrt(p*(1-p)/n)

n=1600;
p_min=p-norminv(0.975)*sqrt(p*(1-p)/n)
p_max=p+norminv(0.975)*sqrt(p*(1-p)/n)