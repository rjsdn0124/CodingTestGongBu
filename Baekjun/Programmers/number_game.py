def solution(A, B):
    answer = 0
    A.sort(reverse=True)
    B.sort(reverse=True)
    l = len(A)
    j = 0
    for i in range(l):
        if A[i] < B[j]:
            j+=1
            answer += 1
    return answer