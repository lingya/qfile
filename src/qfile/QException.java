
package qfile;

/**
 * qfile的使用过程中可能会触发的异常。<br>
 * 在使用时，诸如IOException等其他类型的异常均被捕获并转化成QException
 * @author 肥肥的兔子
 * @version 2016-2-9
 *
 */
public class QException extends Exception{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 带异常信息 
	 * @param msg 异常信息
	 */
	public QException(String msg){
		super(msg);
	}
	
	/**
	 * 带异常具体原因
	 * @param cause 原因
	 */
	public QException(Throwable cause){
		super(cause);
	}

	
	/**
	 * 带信息和原因
	 * @param msg 异常信息
	 * @param cause 原因
	 */
	public QException(String msg,Throwable cause){
		super(msg,cause);
	}
	
}
