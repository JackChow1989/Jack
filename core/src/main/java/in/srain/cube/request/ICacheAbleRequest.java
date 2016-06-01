package in.srain.cube.request;

/**
 * A CacheAble Request wrap
 */
public interface ICacheAbleRequest<T> extends IRequest<T> {

    public int getCacheTime();

    public String getCacheKey();

    public String getAssertInitDataPath();

    /**
     * We need to process the data from data source, do some filter of convert the structure.
     * <p/>
     * As the "Assert Data" is a special data source, we also need to do the same work.
     */
    public T processRawDataFromCache(JsonData rawData);

    /**
     * when data loaded from cache
     *
     * @param cacheData
     * @param outOfDate
     */
    public void onCacheData(T cacheData, boolean outOfDate);

    /**
     * process query data from server
     */
    public void queryFromServer();

    /**
     * disable cache
     * <p/>
     * 1. will not load cache
     * <p/>
     * 2. data will not set to cache
     *
     * @return
     */
    public boolean disableCache();

    /**
     * set a timeout, when request time over this value, cache data will be used.
     *
     * @param timeOut
     */
    public void setTimeout(int timeOut);

    /**
     * Using cache data if existent, ignore whether it is expired or not.
     *
     * @param use
     */
    public void useCacheAnyway(boolean use);
}
