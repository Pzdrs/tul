clear all
clc

sirka=0.01;

%souèin p*(1-p) je maximální, jestliže p=0.5
rozsah=4*0.5*0.5*(norminv(0.975,0,1))^2/(sirka*sirka)