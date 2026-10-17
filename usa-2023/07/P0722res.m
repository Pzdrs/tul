clear all
clc

n2020=845;
n2021=541;

SMP2020=60;
SMP2021=57;

p2020=SMP2020/n2020;
p2021=SMP2021/n2021;

%dosadim do vzorce z kapitoly 7.13
p=(SMP2020+SMP2021)/(n2020+n2021);
roz_min=(p2021-p2020)-sqrt(p*(1-p)*(1/n2020+1/n2021))*norminv(0.975,0,1)
roz_max=(p2021-p2020)+sqrt(p*(1-p)*(1/n2020+1/n2021))*norminv(0.975,0,1)