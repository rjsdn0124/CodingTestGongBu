def solution(n, s):
    answer = []
    if s // n == 0:
        answer.append(-1)
    else:
        spn = s%n
        num = s//n
        for i in range(n):
            answer.append(num)
            if n-i <= spn:
                answer[-1] += 1
    return answer