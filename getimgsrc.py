from __future__ import print_function
from sys import argv
from urllib.request import urlopen
import os
import os.path

text = open('data\jpglink','r')
cnt = 0
for line in text:
	cnt = cnt + 1
	url = urlopen(line)
	source = url.read()
	if os.path.isfile('data\src_' + str(cnt)):
		os.remove('data\src_' + str(cnt))
	writesource = open('data\src_' + str(cnt),'wb')
	writesource.write(source)
	writesource.close