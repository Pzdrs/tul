#lang scheme

(define-struct oddeleni (nazev zamestnanci))

(define podnik (list
                (make-oddeleni "Prodej"
                               '("Anna Bílá" "Lída Žlutá" "Petr Modrý"))
                (make-oddeleni "Vývoj"
                               '("Josef Zelený" "Lenka Růžová" "Alena Černá"))
))

(define (hledej-oddeleni name departments)
  (cond
    [(empty? departments) '()]
    [(je-z-oddeleni? name (car departments)) (oddeleni-nazev (car departments))]
    [else (hledej-oddeleni name (cdr departments))]
    ))

(define (je-z-oddeleni? name department) (define employees (oddeleni-zamestnanci department))
  (cond
       [(empty? employees) #f]
       [(equal? name (car employees)) #t]
       [else (je-z-oddeleni? name (make-oddeleni name (cdr employees)))])) 