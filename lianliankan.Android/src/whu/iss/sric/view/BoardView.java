package whu.iss.sric.view;

import java.util.ArrayList;
import java.util.List;

import whu.iss.sric.android.R;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

public class BoardView extends View {

	/**
	 * xCount x�᷽���ͼ����+1
	 */
	protected  static final int xCount =5;
	/**
	 * yCount y�᷽���ͼ����+1
	 */
	protected static final int  yCount =6;
	/**
	 * map ��������Ϸ����
	 */
	protected int[][] map = new int[xCount][yCount];
	/**
	 * iconSize ͼ���С
	 */
	protected int iconSize;
	/**
	 * iconCounts ͼ�����Ŀ
	 */
	protected int iconCounts=9;
	/**
	 * icons ���е�ͼƬ
	 */
	protected Bitmap[] icons = new Bitmap[iconCounts];
	
	/**
	 * path ������ͨ���·��
	 */
	private Point[] path = null;
	/**
	 * selected ѡ�е�ͼ��
	 */
	protected List<Point> selected = new ArrayList<Point>();
	
	public BoardView(Context context,AttributeSet atts) {
		super(context,atts);
		
		calIconSize();
		
		Resources r = getResources();
		loadBitmaps(1, r.getDrawable(R.drawable.a1));
		loadBitmaps(2, r.getDrawable(R.drawable.a2));
		loadBitmaps(3, r.getDrawable(R.drawable.a3));
		loadBitmaps(4, r.getDrawable(R.drawable.a4));
		loadBitmaps(5, r.getDrawable(R.drawable.a5));
		loadBitmaps(6, r.getDrawable(R.drawable.a6));
		
		
	}
	
	/**
	 * 
	 * ����ͼ��ĳ���
	 */
	private void calIconSize()
    {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) this.getContext()).getWindowManager()
		.getDefaultDisplay().getMetrics(dm);
        iconSize = dm.widthPixels/(xCount);
    }

	/**
	 * 
	 * @param key �ض�ͼ��ı�ʶ
	 * @param d drawable�µ���Դ
	 */
	public void loadBitmaps(int key,Drawable d){
		Bitmap bitmap = Bitmap.createBitmap(iconSize,iconSize,Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		d.setBounds(0, 0, iconSize, iconSize);
		d.draw(canvas);
		icons[key]=bitmap;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		/**
		 * ������ͨ·����Ȼ��·���Լ�����ͼ�����
		 */
		if (path != null && path.length >= 2) {
			for (int i = 0; i < path.length - 1; i++) {
				Paint paint = new Paint();
				paint.setColor(Color.CYAN);
				paint.setStyle(Paint.Style.STROKE);
				paint.setStrokeWidth(3);
				Point p1 = indextoScreen(path[i].x, path[i].y);
				Point p2 = indextoScreen(path[i + 1].x, path[i + 1].y);
				canvas.drawLine(p1.x + iconSize / 2, p1.y + iconSize / 2,
						p2.x + iconSize / 2, p2.y + iconSize / 2, paint);
			}
			Point p = path[0];
			map[p.x][p.y] = 0;
			p = path[path.length - 1];
			map[p.x][p.y] = 0;
			selected.clear();
			path = null;
		}
		/**
		 * �������̵�����ͼ�� ����������ڵ�ֵ����0ʱ����
		 */
		for(int x=0;x<map.length;x+=1){
			for(int y=0;y<map[x].length;y+=1){
				if(map[x][y]>0){
					Point p = indextoScreen(x, y);
					canvas.drawBitmap(icons[map[x][y]], p.x,p.y,null);
				}
			}
		}
		
		/**
		 * ����ѡ��ͼ�꣬��ѡ��ʱͼ��Ŵ���ʾ
		 */
		for(Point position:selected){
			Point p = indextoScreen(position.x, position.y);
			if(map[position.x][position.y] >= 1){
				canvas.drawBitmap(icons[map[position.x][position.y]],
						null,
						new Rect(p.x-5, p.y-5, p.x + iconSize + 5, p.y + iconSize + 5), null);
			}
		}
	}
	
	/**
	 * 
	 * @param path
	 */
	public void drawLine(Point[] path) {
		this.path = path;
		this.invalidate();
	}
	
	/**
	 * ���߷���
	 * @param x �����еĺ�����
	 * @param y �����е�������
	 * @return ��ͼ���������е�����ת������Ļ�ϵ���ʵ����
	 */
	public Point indextoScreen(int x,int y){
		return new Point(x* iconSize , y * iconSize );
	}
	/**
	 * ���߷���
	 * @param x ��Ļ�еĺ�����
	 * @param y ��Ļ�е�������
	 * @return ��ͼ������Ļ�е�����ת���������ϵ���������
	 */
	public Point screenToindex(int x,int y){
		int ix = x/ iconSize;
		int iy = y / iconSize;
		if(ix < xCount && iy <yCount){
			return new Point( ix,iy);
		}else{
			return new Point(0,0);
		}
	}
}
