function [t,y] = euler_imp(f,a,b,y0,h)
    % dam initial hodnoty do output vektoru aby byly dostupny v loopu
    y(1) = y0;
    t(1) = a;
    % pocet iteraci
    n = (b-a)/h;
    for i=1:n
        % cas tohoto kroku - musim vypocitat dopredu protoze to za chvili budu
        % potrebovat
        t(i+1)=t(i) + h;
        % spocitam rychlost jako bych to delal u explicitni eulerovky a to pak
        % pouziju pro vypocet implicitni o radek nize
        y(i+1)=y(i) + h * (f(t(i), y(i)));
    
        % Yn = Yn+1 + hF(xn, yn)
        % https://en.wikipedia.org/wiki/Backward_Euler_method#:~:text=This%20differs%20from,.
        y(i+1)=y(i) + h * f(t(i+1), y(i+1));
    end
end

