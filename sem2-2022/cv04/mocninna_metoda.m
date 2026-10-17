% najde dominantni eigenvalue a jeji eigenvector
function [vec, lambda] = mocninna_metoda(A, y0, tol, maxit)        
    lambda = 0;
    for it = 0 : maxit
        lambda0 = lambda;
        % mocninny krok
        Ay0 = A * y0;
        [~,X] = max(abs(Ay0));
        % nejvetsi vlastni cislo
        lambda = Ay0(X);       
        y0 = Ay0 / lambda;
        % pokud je nynejsi iterace dostacujici tak koncime computations
        if (abs(lambda - lambda0) < tol) 
           break; 
        end    
    end
    % korespondujici vektor k poslednimu vlastnimu cislu
    vec = y0 / norm(y0, 1);      
end