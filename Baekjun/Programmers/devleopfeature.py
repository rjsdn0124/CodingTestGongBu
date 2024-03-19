def solution(progresses, speeds):
    answer = []
    l = len(progresses)
    baepo_timing = []
    prev = 0
    for i in range(l):
        p = 100 - progresses[i]
        s = speeds[i]
        bt = p//s + (1 if not p%s == 0 else 0)
        if prev < bt:
            answer.append(1)
            prev = bt
        else:
            answer[-1] += 1

    return answer