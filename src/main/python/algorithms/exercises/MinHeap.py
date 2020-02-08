class MinHeap:

    def __init__(self, array):
        # Do not edit the line below.
        self.heap = self.buildHeap(array)

    def buildHeap(self, array):
        return sorted(array)

    # Note: all i1 variables are 1-based, so index i1 represents self.heap[i1-1]

    # swaps value at i1 with its parent
    def swapWithParent(self, i1):
        self.heap[i1 - 1], self.heap[i1 // 2 - 1] = self.heap[i1 // 2 - 1], self.heap[i1 - 1]

    # finds child of i1 that is lowest and returns it, or -1 if no children is lower
    def findIndexToSiftDown(self, i1):
        a1 = 2 * i1
        b1 = a1 + 1
        lower = i1
        if a1 <= len(self.heap) and self.heap[a1-1] < self.heap[lower-1]:
            lower = a1
        if b1 < len(self.heap) and self.heap[b1-1] < self.heap[lower-1]:
            lower = b1
        return lower if lower != i1 else -1

    def siftDown(self):
        i1 = self.findIndexToSiftDown(1)
        while i1 > 0:
            self.swapWithParent(i1)
            i1 = self.findIndexToSiftDown(i1)

    def siftUp(self):
        i1 = len(self.heap)
        while i1 > 1 and self.heap[i1 - 1] < self.heap[i1 // 2 - 1]:
            self.swapWithParent(i1)
            i1 //= 2

    def peek(self):
        return self.heap[0]

    def remove(self):
        self.heap[0] = self.heap.pop(-1)
        self.siftDown()

    def insert(self, value):
        self.heap.append(value)
        self.siftUp()


array = [991, -731, -882, 100, 280, -43, 432, 771, -581, 180, -382, -998, 847, 80, -220, 680, 769, -75, -817, 366, 956, 749, 471, 228, -435, -269, 652, -331, -387, -657, -255, 382, -216, -6, -163, -681, 980, 913, -169, 972, -523, 354, 747, 805, 382, -827, -796, 372, 753, 519, 906]
minHeap = MinHeap(array)
print(minHeap.heap)
minHeap.remove()
print(minHeap.heap)
