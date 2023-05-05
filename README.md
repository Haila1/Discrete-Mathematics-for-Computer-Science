# Discrete-Mathematics-for-Computer-Science
Prime Generation
Write a function that, given a (sufficiently large) range of possible numbers between nmin and nmax, repeatedly does the following:
 1
• choose a random integer between nmin and nmax
• test whether its prime using the Miller-Rabin test
millerRabinIsPrime?(n, k):
input : n is a candidate prime number; k is a certainty parameter output: decision whether the number is prime or composite
write n−1 as 2rd for an odd number d
while weve done fewer than k tests: do
choose a random a ∈ 1, ..., n − 1
σ = ⟨ad,a2d,a4d,a8d,...,a2rd⟩ mod n
if σ̸=⟨....,1⟩orσ=⟨....,x,1,...⟩forsomex∈/1,n−1then
return ”composite” end
 end
return ”prime”
