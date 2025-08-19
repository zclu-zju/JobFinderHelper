import requests
import redis
from lxml import html
from tqdm import tqdm


# 配置 Redis
REDIS_CONFIG = {
    "host": "hwbt.py00.top",
    "port": 6379,
    "db": 0
}

# 目标 URL
URL = "http://job.mohrss.gov.cn/zdqyzpxx/index_0.jhtml"

# 连接 Redis
redis_client = redis.Redis(**REDIS_CONFIG, decode_responses=True)

# 获取网页 HTML
def fetch_html(url):
    headers = {
        "User-Agent": "Mozilla/5.0"
    }
    response = requests.get(url, headers=headers)
    return response.text if response.status_code == 200 else None

# 解析 HTML
def parse_html(content):
    tree = html.fromstring(content)
    results = []

    # XPath 路径: /html/body/div[2]/div[2]/div[1]/div[2]/div[2]/ul/li/a
    links = tree.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/div[2]/ul/li/a")

    for a in links:
        link = a.get("href")
        title = a.text.strip() if a.text else ""

        if link and title:
            results.append((link, title))

    return results

# 存入 Redis
def save_to_redis(data):
    for url, title in tqdm(data):
        # 先检查 Redis 是否已存在
        if redis_client.sismember("visited_urls", url):
            print(f"已存在，跳过: {url}")
            continue

        # 存入 Redis
        redis_client.hset("web_links", url, title)
        redis_client.sadd("visited_urls", url)
        print(f"已存入 Redis: {url} -> {title}")

# 执行爬取
html_content = fetch_html(URL)
if html_content:
    parsed_data = parse_html(html_content)
    save_to_redis(parsed_data)
