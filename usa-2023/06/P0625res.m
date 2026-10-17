clear all
clc
% odecitam od jednicky protoze tcdf vraci probabilitu kdy pro x <= ale ja
% chci x >
P2=1-tcdf(1,2)
P4=1-tcdf(1,4)
P10=1-tcdf(1,10)
P100=1-tcdf(1,100)
Pnorm=1-normcdf(1,0,1)