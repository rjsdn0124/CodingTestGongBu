def solution(skill, skill_trees):
    answer = 0
    dic = {}
    for i in range(len(skill)):
        dic[skill[i]] = i
    for tree in skill_trees:
        now = 0
        can_use = True
        for c in tree:
            if c in dic:
                if dic[c] > now:
                    can_use = False
                    break
                else:
                    now += 1
        if can_use:
            answer += 1
    return answer