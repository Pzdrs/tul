% https://www.youtube.com/watch?v=Yl_I4iYlyus
% https://www.youtube.com/watch?v=6p-a7ZoGk18
% Back substitution
% https://www.youtube.com/watch?v=_q5HpaOhFes

function [x, U] = gauss_elim(A,b)
    [m,n]=size(A);
    if m~=n, error('Matice musí být čtvercová'); end
    % determinant check nefungoval, takze je tady check s urcitou toleranci
    % singularity
    if rcond(A) < 1e-12
        error("singular"); 
    end
    
    % LU decomp
    % https://en.wikipedia.org/wiki/LU_decomposition#LU_factorization_with_partial_pivoting
    L = eye(n); P = L; U = A;
    for k=1:n
        [~, m]=max(abs(U(k:n,k)));
        m = m+k-1;
        if m~=k
            % swapnu radek
            temp=U(k,:);
            U(k,:)=U(m,:);
            U(m,:)=temp;

            % swapnu stejnym zpusobem i permutation matici
            temp=P(k,:);
            P(k,:)=P(m,:);
            P(m,:)=temp;
            if k >=2
                temp=L(k,1:k-1);
                L(k,1:k-1)=L(m,1:k-1);
                L(m,1:k-1)=temp;
            end
        end
        for j=k+1:n
            L(j,k)=U(j,k)/U(k,k);
            U(j,:)=U(j,:)-L(j,k)*U(k,:);
        end
    end
    disp('L = ');disp(L);
    disp('U = ');disp(U);
    % reorder matrix b using P, ktery si pamatuje co jsme delali Ackem
    % basically
    Pb = P*b;
    d = L\Pb;
    x = U\d;
    disp('x = ');disp(x);
end