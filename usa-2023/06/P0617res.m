clear all
clc

n=1000;
p=0.168;
pi=0.55

%vypočtu z, které je z N(0,1), z něj následně pravděpodobnost
z=(p-pi)/sqrt(pi*(1-pi)/n);
pravd=normcdf(z,0,1)
