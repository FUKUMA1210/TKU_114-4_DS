public class GenericResultDemo {

    static class Result<T> {
        private boolean success;
        private String message;
        private T data;

        public Result(boolean success, String message, T data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public T getData() {
            return data;
        }
    }

    public static void main(String[] args) {

        Result<String> stringResult =
                new Result<>(true, "取得姓名成功", "小明");

        Result<Integer> intResult =
                new Result<>(true, "取得分數成功", 90);

        Result<String> failResult =
                new Result<>(false, "取得資料失敗", null);

        System.out.println("String結果：");
        System.out.println("成功：" + stringResult.isSuccess());
        System.out.println("訊息：" + stringResult.getMessage());
        System.out.println("資料：" + stringResult.getData());

        System.out.println();

        System.out.println("Integer結果：");
        System.out.println("成功：" + intResult.isSuccess());
        System.out.println("訊息：" + intResult.getMessage());
        System.out.println("資料：" + intResult.getData());

        System.out.println();

        System.out.println("失敗結果：");
        System.out.println("成功：" + failResult.isSuccess());
        System.out.println("訊息：" + failResult.getMessage());
        System.out.println("資料：" + failResult.getData());
    }
}