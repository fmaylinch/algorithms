import heapq


class MeanCalculator:

    def __init__(self):
        # to calculate the mean, great_heap length will always be the same
        # as lower_heap or 1 more (so we only need the top of the heaps)
        self.lower_heap = []  # lower numbers (negated, to have a max heap)
        self.great_heap = []  # greater numbers

    def dump(self):
        return self.median(), self.lower_heap, self.great_heap

    def add(self, x):
        if not self.great_heap:
            heapq.heappush(self.great_heap, x)
        elif len(self.great_heap) > len(self.lower_heap):  # push to lower_heap to equilibrate
            if x <= self.great_heap[0]:  # we can put the lower x in lower_heap
                heapq.heappush(self.lower_heap, -x)
            else:  # we need to move the lower number at the top of great_heap
                heapq.heappush(self.lower_heap, -heapq.heappushpop(self.great_heap, x))
        else:  # heaps have equal length, push to great_heap
            if x >= -self.lower_heap[0]:
                heapq.heappush(self.great_heap, x)
            else:
                heapq.heappush(self.great_heap, -heapq.heappushpop(self.lower_heap, -x))
        return self.median(), self.lower_heap, self.great_heap

    def median(self):
        if len(self.great_heap) > len(self.lower_heap):
            return self.great_heap[0]
        else:
            # lower_heap contains negated numbers so we subtract
            return (self.great_heap[0] - self.lower_heap[0]) / 2


mc = MeanCalculator()
a = [1, 6, 3, 5, 4, 2]
for x in a:
    mc.add(x)
print(mc.median())
print(mc.dump())

