package transaction_aop;

/**
 * Copyright 2018-2028 Baoge All Rights Reserved.
 * Author: Shao Xu Bao <15818589952@163.com>
 * Date:   2018/10/3
 */
public interface BankService {
    public void transfer(int fromId, int toId, int amount);
}
