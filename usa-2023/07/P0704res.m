clear all
clc

mu=44;
sd=4;
n=12;

%nutno poèítat dle vzorce, protože funkce ttest je založena na znalosti dat
%tinv 0.95, protože se u oboustranných intervalù zadává 1-alfa/2
mu_min=mu-(sd/sqrt(n))*tinv(0.95,n-1)
mu_max=mu+(sd/sqrt(n))*tinv(0.95,n-1)