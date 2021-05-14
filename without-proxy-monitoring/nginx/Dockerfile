FROM ubuntu:20.04

RUN apt-get update \
  && DEBIAN_FRONTEND=noninteractive \
     TZ=UTC \
      apt-get install --no-install-recommends --no-install-suggests -y \
        build-essential autoconf libtool pkg-config ca-certificates \
        cmake gcc g++ python3 git nginx libpcre3-dev

# forward request and error logs to docker log collector
RUN ln -sf /dev/stdout /var/log/nginx/access.log \
	&& ln -sf /dev/stderr /var/log/nginx/error.log

CMD ["/usr/sbin/nginx", "-g", "daemon off;"]
EXPOSE 80