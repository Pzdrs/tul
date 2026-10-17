% https://www.youtube.com/watch?v=OzFuihxtbtA
% Vyhoda bisekce je takova, ze pokud passed in interval obsahuje root, tato
% metoda ho najde no matter what, unlike newton a secent - je sance ze
% zdiverguji
function [x,res,k] = bisection(f,a,b,tol,maxit)
    x = b;
    k = 0;
    res = 0;
    lowerBound = a;
    upperBound = b;
    
    % respektuju toleranci a zaroven maxit
    while (abs(f(x)) > tol) && (k < maxit)
        % prepulim interval, x == middle
        x = 0.5 * (lowerBound + upperBound);   
    
        % rozhodnu, jak bude nas novy interval vypadat
        if (f(x) * f(b) < 0)
            lowerBound = x;
        else
            upperBound = x;
        end

        % counter++
        k = k + 1;
        % residuum
        res = abs(f(x));
    end
end
