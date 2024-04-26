def solution(n,a,b):
    answer = 1
    while not((a - b == 1 and a % 2 == 0) or (b - a == 1 and b % 2 == 0)):
        a = (a+1)//2
        b = (b+1)//2
        answer +=1
    return answer