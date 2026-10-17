clear all
clc

%otevøe data uložená v souboru
x=importdata('P0812.mat');
str_hodnota=mean(x)
rozptyl=var(x);

[p,h,stats]=signtest(x,220)

if p>0.05
    'Hypotézu H0 na hladinì významnosti 5 % nezamítáme'
else
    'Hypotézu H0 na hladinì významnosti 5 % zamítáme'
end