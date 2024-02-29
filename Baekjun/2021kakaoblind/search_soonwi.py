# 정확도 통과 코드
# def solution(info, query):
#     answer = [0 for _ in range(len(query))]
    
#     for i, q in enumerate(query):
#         new_q = q.replace('and ','').split(' ')
    
#         for inf in info:
#             new_info = inf.split(' ')
#             if int(new_info[-1]) >= int(new_q[-1]):
#                 result = 1
#                 for k in range(4):
#                     if new_q[k] != '-' and new_q[k] != new_info[k]:
#                         result = 0
#                 answer[i] += result
#     return answer

# 정확도 효유ㅜㄹ성 둘 다 통과 코드. 이진 탐색을 써야함!
def solution(info, query):
    answer = []
    
    inf_dict = {}
    for lang in ['java','cpp','python','-']:
        for position in ['backend','frontend','-']:
            for year in ['junior', 'senior', '-']:
                for food in ['chicken','pizza','-']:
                    inf_dict[' '.join([lang,position, year, food])] = []
                    
    for inf in info:
        inf_split = inf.split(' ')
        for lang in [inf_split[0],'-']:
            for position in [inf_split[1],'-']:
                for year in [inf_split[2], '-']:
                    for food in [inf_split[3],'-']:
                        inf_dict[' '.join([lang,position, year, food])].append(int(inf_split[-1]))

    for k in inf_dict:
        inf_dict[k].sort()
        
    for q in query:
        new_q = q.replace('and ','').split(' ')
        info_arr = inf_dict[' '.join(new_q[:-1])]
        l = len(info_arr)
        std = int(new_q[-1])
        
        left = 0
        right = l
        mid = 0
        while left < right:
            mid = (right + left) // 2
            if info_arr[mid] >= std:
                right = mid
            else:
                left = mid + 1
        
        answer.append(l - left)
    return answer