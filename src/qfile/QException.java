
package qfile;

/**
 * qfile��ʹ�ù����п��ܻᴥ�����쳣��<br>
 * ��ʹ��ʱ������IOException���������͵��쳣��������ת����QException
 * @author �ʷʵ�����
 * @version 2016-2-9
 *
 */
public class QException extends Exception{

	private static final long serialVersionUID = 1L;
	
	/**
	 * ���쳣��Ϣ 
	 * @param msg �쳣��Ϣ
	 */
	public QException(String msg){
		super(msg);
	}
	
	/**
	 * ���쳣����ԭ��
	 * @param cause ԭ��
	 */
	public QException(Throwable cause){
		super(cause);
	}

	
	/**
	 * ����Ϣ��ԭ��
	 * @param msg �쳣��Ϣ
	 * @param cause ԭ��
	 */
	public QException(String msg,Throwable cause){
		super(msg,cause);
	}
	
}
