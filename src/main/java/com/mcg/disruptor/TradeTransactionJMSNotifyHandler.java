package com.mcg.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.EventTranslator;

import java.util.Random;

public class TradeTransactionJMSNotifyHandler implements EventHandler<TradeTransaction> {

    @Override
    public void onEvent(TradeTransaction event, long sequence,
                        boolean endOfBatch) throws Exception {
        //do send jms message
        System.out.println(event.getId());
    }
}


class TradeTransactionEventTranslator implements EventTranslator<TradeTransaction> {
    private Random random = new Random();

    @Override
    public void translateTo(TradeTransaction event, long sequence) {
        this.generateTradeTransaction(event);
    }

    private TradeTransaction generateTradeTransaction(TradeTransaction trade) {
        trade.setPrice(random.nextDouble() * 9999);
        return trade;
    }
}  
  
  
