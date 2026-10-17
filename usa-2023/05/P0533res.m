clear all
clc

%otevøe data uložená v souboru
x=importdata('P0533.mat');
boxplot(x)
% 4 nejvyšší data jsou odlehlá, budou odstranìny
x=sort(x);
delka=length(x);
x=x(1:delka-4);
%boxplot(x)
[mu,sigma]=normfit(x)