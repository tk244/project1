package common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class commonClass {

	/**
	* ��������n�b�V�������郁�\�b�h
	*
	* @param text �n�b�V��������e�L�X�g
	*
	* @return �n�b�V���������v�Z�l(16�i��)
	*/
	public String encryptStr(String text) {
	    // �ϐ�������
	    MessageDigest md = null;
	    StringBuffer buffer = new StringBuffer();
	 
	    try {
	        // ���b�Z�[�W�_�C�W�F�X�g�C���X�^���X�擾
	        md = MessageDigest.getInstance("SHA-256");
	    } catch (NoSuchAlgorithmException e) {
	        // ��O�������A�G���[���b�Z�[�W�o��
	        System.out.println("�w�肳�ꂽ�Í����A���S���Y��������܂���");
	    }
	 
	    // ���b�Z�[�W�_�C�W�F�X�g�X�V
	    md.update(text.getBytes());
	 
	    // �n�b�V���l���i�[
	    byte[] valueArray = md.digest();
	 
	    // �n�b�V���l�̔z������[�v
	    for(int i = 0; i < valueArray.length; i++){
	        // �l�̕����𔽓]�����A16�i���ɕϊ�
	        String tmpStr = Integer.toHexString(valueArray[i] & 0xff);
	 
	        if(tmpStr.length() == 1){
	            // �l���ꌅ�������ꍇ�A�擪��0��ǉ����A�o�b�t�@�ɒǉ�
	            buffer.append('0').append(tmpStr);
	        } else {
	            // ���̑��̏ꍇ�A�o�b�t�@�ɒǉ�
	            buffer.append(tmpStr);
	        }
	    }
	 
	    // ���������n�b�V���v�Z�l��ԋp
	    return buffer.toString();
	}
}
