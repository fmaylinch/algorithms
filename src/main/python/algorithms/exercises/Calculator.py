class Calculator:

    def __init__(self, exp: str):
        self.exp = exp
        self.idx = 0

    def calculate(self) -> int:
        return self.operator_expression()

    def operator_expression(self) -> int:
        result = 0
        sign = 1
        while True:
            result += sign * self.unary_expression()
            if self.next_token_is_operator():
                op = self.consume_token()
                sign = 1 if op == '+' else -1
            else:
                break
        return result

    def consume_token(self, expected: str = None) -> str:
        if self.idx >= len(self.exp):
            raise Exception('Reached end of expression')
        token = self.exp[self.idx]
        if expected and token != expected:
            raise Exception(f'Expected token {expected} but found {token}')
        # print(f'Consumed token {token}')
        self.idx += 1
        return token

    def next_token_is_operator(self) -> bool:
        return self.next_token() in ('+', '-')

    def next_token(self) -> str:
        return self.exp[self.idx] if self.idx < len(self.exp) else None

    def unary_expression(self) -> int:
        if self.next_token() == '-':
            self.consume_token('-')
            return - self.expression()
        else:
            return self.expression()

    def expression(self) -> int:
        if self.next_token() == '(':
            self.consume_token('(')
            result = self.operator_expression()
            self.consume_token(')')
            return result
        else:  # number
            return int(self.consume_token())


def calculate(exp: str, idx: int = 0) -> (int, int):

    result = 0
    op = '+'

    while idx < len(exp):

        token = exp[idx]
        value = 0

        if token in ('-', '+'):
            op = token
        elif token == '(':
            value, idx = calculate(exp, idx+1)
        elif token == ')':
            return result, idx
        else:
            value = int(token)

        if op == '+':
            result += value
        elif op == '-':
            result -= value

        idx += 1

    return result, idx


print(Calculator('7-(2+1)+2').calculate())
print(calculate('7-(2+1)+2'))
