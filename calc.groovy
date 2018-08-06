BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
print "Enter the expression: "
 def math = br.readLine()
def arStr = (math.split("(?<=\\d)(?=[+\\-*\\\\/()^])|(?<=[+\\-*\\\\/()^])|(?=[+\\-*\\\\/()^])|(?<=[+\\-*\\\\/()^])(?=\\d)")).toList()
def stack = []
def out = []
 def oper(o) {
    def brackets = ['(',')','*','/','+','-','^']
    for (l=0; l < brackets.size(); l++) {
        if (brackets[l] == o) {
            return o
        }
    }
}
 static byte queue(p) {
    switch (p) {
        case '(': return 0;
        case ')': return 1;
        case '+': return 2;
        case '-': return 3;
        case '*': return 4;
        case '/': return 4;
        case '^': return 5;
        default: return 6;
    }
}
 for (i=0; i < arStr.size(); i++) {
    if (arStr[i].isNumber()) {
        out.add(arStr[i])
    }
    if (oper(arStr[i])== arStr[i]) {
        if (arStr[i] == '(') {
            stack.add(arStr[i])
        }
        else if (arStr[i] == ')') {
            stack.add(arStr[i])
            def indexend = stack.findIndexValues { it == ')' }
            def indexstart = stack.findIndexValues { it == '(' }
            def del = indexstart.last().toInteger()
            for (k=indexend.first().toInteger(); k > indexstart.last().toInteger(); k--) {
                out.add(stack[k])
            }
            for (k=indexend.first().toInteger(); k > indexstart.last().toInteger(); k--) {
                stack.remove(del)
            }
            stack.remove(del)
            out.removeAll(')')
        }
        else {
            if (stack.size() > 0)
                if (queue(arStr[i]) <= queue(stack.last())){
                    def delete = stack.size().toInteger()-1
                    out.add(stack.last())
                }
            stack.add(arStr[i])
        }
    }
}
 for (i=stack.size()-1; i >= 0; i--){
    out.add(stack.last())
    stack.remove(i)
}
ref = out.size()
for (i=0; i < ref; i++) {
    if (oper(out[i]) == out[i]) {
        a = out[i-1]
        b = out[i-2]
        switch (out[i])
        {
            case '+': out[i] = b.toFloat() + a.toFloat()
                out.remove(i-1)
                out.remove(i-2)
                break
            case '-': out[i] = b.toFloat() - a.toFloat()
                out.remove(i-1)
                out.remove(i-2)
                break
            case '*': out[i] = b.toFloat() * a.toFloat()
                out.remove(i-1)
                out.remove(i-2)
                break
            case '/': out[i] = b.toFloat() / a.toFloat()
                out.remove(i-1)
                out.remove(i-2)
                break
            case '^':
                for (step=0; step < a.toInteger()-1; step++){
                    out[i-2] = out[i-2].toFloat() * b.toFloat()
                    //println(out[i-2])
                }
                out.remove(i)
                out.remove(i-1)
                break
        }
        i = 0
    }
    if (out.size() == 1) {
        break
    }
}
println('Result= ' + String.format("%.4g%n()", out[0]))
