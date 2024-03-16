def solution(expression):
    answer = 0
    
    def switch(a,b,c):
        if c == '*':
            a *= b
        elif c == '+':
            a += b
        else:
            a -= b
        return a
    
    def rec(arr):
        res = []
        if len(arr) == 1:
            return [arr]
        for i in range(len(arr)):
            for op in rec(arr[:i] + arr[i+1:]):
                res.append(op + [arr[i]])
        return res
    
    ops_arr = rec(['*','+','-'])
    
    op_in_exp = []
    nums = []
    i = 0
    l=len(expression)
    
    while i < l:
        temp = ''
        while i < l and not expression[i] in ['*','+','-']:
            temp += expression[i]
            i+=1
        nums.append(int(temp))
        if i < l:
            op_in_exp.append(expression[i])
        i += 1
        
    for ops in ops_arr:
        op_stack = op_in_exp
        num_stack = nums
        for op in ops:
            tos = []
            tns = [num_stack[0]]
            for i in range(len(op_stack)):
                if op_stack[i] == op:
                    tns[-1] = switch(tns[-1],num_stack[i+1],op)
                else:
                    tos.append(op_stack[i])
                    tns.append(num_stack[i+1])
            if len(tns) == 1:
                answer = max(answer,tns[0] if tns[0] > 0 else -tns[0])
                break
            op_stack = tos
            num_stack = tns
    return answer