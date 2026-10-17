clc;close all;
A = full(gallery('poisson',5));
A2 = full(gallery('lehmer',20));
A3 = full(gallery('dorr',5)); 
applyMethod("poisson", A, ones(25,1), 1e-7, 1000)
applyMethod("lehmer", A2, ones(20,1), 1e-7, 1000)
applyMethod("dorr", A3, ones(5,1), 1e-7, 1000)
 
 
function applyMethod(method, A, b, tol, iter)
	[~, flag, ~, it, rv] = gauss_seidel(A, b, tol, iter);
    % pokud konvergovala
    if flag == 0
    	loglog (1:it+1, rv)
    end
    
   	title('Graf závislosti rezidua na iteracích')
   	xlabel("Počet iterací (it)")
	ylabel("Rezidum (rv)")
    hold on
	
    [~, flag, ~, it, rv] = jacobi(A, b, tol, iter);
    % pokud konvergovala
    if flag == 0
    	loglog (1:it+1, rv)
    end
    [~, flag, ~, it, rv] = pcg(A, b, tol, iter);
    % pokud konvergovala
    if flag == 0
    	loglog (1:it+1, rv)
    end

    % legendy prubehu grafu
    legend(upper(method) + " Gauss seidel", upper(method) + " Jacobi", upper(method) + " PCG")
    % save to file
	saveas(gcf,'graph_'+method+'.png')
    
    close all;clc;clear;
end