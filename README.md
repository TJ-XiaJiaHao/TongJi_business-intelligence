# Business-Intelligence

## Kernel 
运行方式：将 `Amazon0601.txt` 放在与 `main.cpp` 相同目录下，编译运行 `main.cpp` 即可

## API
* URL: /products/products
  * Method: POST
  * Param: 
    * @param info，格式为 “原始商品个数&目标推荐商品个数&商品1&商品2&...”，如"1&2&3"
  * Return: 
    * 为list，其中每单元有以下信息
    ![](https://github.com/justPlay197/Business-Intelligence/blob/master/后端传回格式.png)
