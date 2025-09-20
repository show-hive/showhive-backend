#!/bin/bash
# ============================================
# Redis 사용자 발급 쿠폰 Cluster 초기화 스크립트
# ============================================

# 노드 리스트 (서비스 이름:포트)
NODES=(
  "redis-coupon-node1:6379"
  "redis-coupon-node2:6379"
  "redis-coupon-node3:6379"
  "redis-coupon-node4:6379"
  "redis-coupon-node5:6379"
  "redis-coupon-node6:6379"
)

# 컨테이너가 뜰 때까지 잠시 대기
echo "Redis 노드 시작 대기 중..."
sleep 5

# 클러스터 상태 확인
EXISTING=$(docker exec -it redis-coupon-node1 redis-cli cluster info | grep cluster_state)

if [[ "$EXISTING" == *"ok"* ]]; then
  echo "Cluster가 이미 초기화되었습니다."
  exit 0
fi

# 클러스터 생성
echo "Redis Cluster 생성 중..."
docker exec -i redis-coupon-node1 redis-cli --cluster create \
  "${NODES[@]}" --cluster-replicas 1 --cluster-yes
