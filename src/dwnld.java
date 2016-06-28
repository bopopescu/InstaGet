import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.*;
import java.util.Scanner;

public class dwnld {

	public static void main(String[] args) throws IOException, InterruptedException {
		File dir = new File("data");
		dir.mkdir();
		dir = new File("imgs");
		dir.mkdir();
		ArrayList<String> urls = new ArrayList<String>();
		String temp = "";
		char rbrack = (char)123;
		char lbrack = (char)125;
		Scanner kb = new Scanner(System.in);
		System.out.print("Enter your username: ");
		String input = kb.next();
		PrintWriter weburl = new PrintWriter("data\\wob");
		weburl.print("http://www.instagram.com/" + input);
		weburl.close();
        String[] command = {"cmd.exe", "/C", "Start /wait", "rungetsource.cmd"};
		Process run = Runtime.getRuntime().exec(command);
		run.waitFor();
		run.destroyForcibly();
		Scanner filereader = new Scanner(new File("data\\pagesource"));
		while(filereader.hasNext()) {
			temp = filereader.nextLine();
			while(temp.contains(lbrack + ", " + rbrack +  "\"code\": \"")) {
				temp = temp.substring(temp.indexOf(lbrack + ", " + rbrack +  "\"code\": \"") + 13);
				urls.add(temp.substring(0, 11));
			}
		}
		for(int i = 0; i < urls.size(); i++) {
			urls.set(i, "http://www.instagram.com/p/" + urls.get(i));
		}
		PrintWriter jpgs = new PrintWriter("data\\jpglink");
		for(int i = 0; i < urls.size(); i++) {
			jpgs.println(urls.get(i));
		}
		jpgs.close();
		String[] cmd2 = {"cmd.exe", "/C", "Start", "rungetimgsrc.cmd"};
		Process run2 = Runtime.getRuntime().exec(cmd2);
		run2.waitFor();
		run2.destroyForcibly();
		BufferedImage img = null;
		int cnt = 1;
		File f = new File("data\\src_" + cnt);
		Scanner k2 = new Scanner(f);
		String src = null;
		while (cnt <= urls.size()) {
			f = new File("data\\src_" + cnt);
			k2 = new Scanner(f);
			while(k2.hasNext()) {
				temp = k2.nextLine();
				if(temp.contains("<meta property=\"og:image\" content=\""))
					src = temp.substring(39, temp.indexOf("jpg") + 3);
			}
			try {
				img = ImageIO.read(new URL(src));
			}
			catch (IIOException except) {
			}
			ImageIO.write(img, "jpg", new File("imgs\\img_" + cnt + ".jpg"));
			f.delete();
			cnt++;
		}
		kb.close();
		filereader.close();
		k2.close();
	}

}
