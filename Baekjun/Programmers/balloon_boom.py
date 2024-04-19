
# 내 코드.
def solution(a):
    answer = 0
    l = len(a)
    na = [[a[i],i] for i in range(l)]
    na.sort()
    mini = l+1
    maxi = -1
    for v,i in na:
        if  mini > i or i > maxi:
            answer += 1
        mini = min(mini,i)
        maxi = max(maxi,i)
    return answer

# 다른 사람의 최적화된 풀이.
# def solution(a):
#     answer = 1
#     M = min(a)
#     for _ in range(2):
#         m = a[0]
#         i = 1
#         # 가장 작은게 내 오른쪽에 있으니까 내 오른 쪽은 가장 작은 값이 다 이김. 그래서 한 칸씩 가면서 나까지 올때까지 가장 작은게 누군지 찾는거지! 만약 내가 가장 작으면 답 += 1
#         while m != M:
#             if m >= a[i]:
#                 m = a[i]
#                 answer += 1
#             i += 1
#         a.reverse()
#     return answer