clear all
clc

p=59/320;
n=320;

p_min=p-norminv(0.975)*sqrt(p*(1-p)/n)
p_max=p+norminv(0.975)*sqrt(p*(1-p)/n)

n_min=p_min*20000
n_max=p_max*20000