# 시간초과 났떤 코드... 50만 x 50만이 나오는 케이스가 생김..
# def solution(number, k):
#     answer = ''
#     mi = 0
#     while k < len(number):
#         k += 1
#         for i in range(mi, k):
#             mi = i if number[mi] < number[i] else mi
#         answer = ''.join([answer,number[mi]])
#         mi += 1
#     return answer

# 스택을 이용해서 푸는 방법. 이런 방법을 생각하기 좀 쉽지 않은 것 같다. 익숙해지는게 관건!
def solution(number, k):
    answer = ''
    ta = []
    for i in range(len(number)):
        now = number[i]
        while k > 0 and len(ta) > 0 and ta[-1] < now:
            ta.pop()
            k -= 1
        ta.append(now)
    ta = ta[:-k] if k > 0 else ta
    answer = ''.join(ta)
    return answer