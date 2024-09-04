#include <iostream>
#include <list>
#include <unordered_map>

using namespace std;

int LRU(int pages[], int n, int capacity) {
	unordered_map<int, list<int>::iterator> pageMap;
	list<int> pageList;
	int pageFaults = 0;

	for (int i = 0; i < n; ++i)
		if (pageMap.find(pages[i]) == pageMap.end()) {
			if ((int)pageList.size() == capacity) {
				int leastRecentlyUsed = pageList.back();
				pageList.pop_back();
				pageMap.erase(leastRecentlyUsed);
			}
			pageList.push_front(pages[i]);
			pageMap[pages[i]] = pageList.begin();
			++pageFaults;
		} else {
			pageList.erase(pageMap[pages[i]]);
			pageList.push_front(pages[i]);
			pageMap[pages[i]] = pageList.begin();
		}

	return pageFaults;
}

int main() {
	int pages[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};
	int n = sizeof(pages) / sizeof(pages[0]);
	int capacity = 3;  // Number of frames
	int pageFaults = LRU(pages, n, capacity);
	cout << "Total page faults using LRU: " << pageFaults << endl;
	return 0;
}
