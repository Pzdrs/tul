clear all
clc

x=[987,1001,993,994,993,1005,1007,999,995,1002];

%ad a
%parametr 1000 zatím není potřeba, využije se v kapitole 8
[h,p,ci,stats] = ttest(x,1000)

%ad b
[h,p,ci,stats] = ttest(x,1000,0.1)

%ad c
[h,p,ci,stats] = ttest(x,1000,0.05,'right')