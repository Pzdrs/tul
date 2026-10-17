function Y=vektor(x)
ro=sqrt(x(1))^2+x(2)^2;
if ro==0
    fi=0;
else
    fi=atan(x(2)/x(1));
end
if x(1) < 0
    fi=fi+pi;
end
Y=vektorrofi(ro,fi);
end