clear all
clc

%vygeneruji náhodná čísla z desetic z rovnoměrného rozdělení
A=lognrnd(3,2, 10000,1000);
for i=1:10000
    B(i)=sum(A(i,:))/1000;
end
hist(B,1000)