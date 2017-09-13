package per.bos.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import per.bos.domain.User;
import per.bos.utils.BOSUtils;

/**
 * �Զ����������� �û�δ��¼���Զ���ת����¼ҳ��
 * @author ����ҫ
 *
 */
public class BosLoginInterceptor extends MethodFilterInterceptor{
	
	//���ط���
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User user = BOSUtils.getUser();
		if(user == null){
			//û�е�¼ ���ؽ����
			return "login";
		}
		//����
		return invocation.invoke();
	}
	
}
