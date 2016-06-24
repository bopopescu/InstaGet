from __future__ import print_function
from sys import argv
from urllib.request import urlopen
import os
import os.path

text = open('data\wob','r')
url2 = text.read()
url = urlopen(url2)
source = url.read()
if os.path.isfile('data\pagesource'):
	os.remove('data\pagesource')
s2 = open('data\pagesource','wb')
s2.write(source)
s2.close
