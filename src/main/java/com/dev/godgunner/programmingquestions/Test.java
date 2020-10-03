package com.dev.godgunner.programmingquestions;

public class Test {




//    public class ExchangeResolverImpl implements ExchangeResolver {
//
//        private int lastRun;
//        private Set<String> openExchanges;
//
//        @Async
//        @Override
//        public boolean isOpen(String exchange) {
//            int currentRun = System.currentTimeMillis();
//            if((currentRun-lastRun > 15*60*1000) || lastRun == 0){
//                //check
//                lastRun = currentRun;
//                openExchanges = getOPenExchangesFromAPI();
//            }
//            return openExchanges.contains(exchange);
//        }
//
//        private static Set<String> getOPenExchangesFromAPI() {
//            //already implemented
//
//        }

//    }
}
