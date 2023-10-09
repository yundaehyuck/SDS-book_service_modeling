pipeline 
{
	agent any
	// tools {
	// 	gradle 'gradle 7.6.1'
	// }
	environment {
		PROJECT = 'ae-book'
		APP_SPRING_API = 'backend'
		APP_AI_API = 'ai'
		APP_BATCH = 'batch'
	}
	stages {
		//===================================== Environment =====================================
		// docker-compose 대신 k8s 사용 고려 중
		// stage('environment') {
		// 	when {
		// 		changeset "env-config/**/*"
		// 	}
		// 	steps {
		// 		echo 'Environment Settings Start'
		// 		sh 'docker-compose -f env-config/docker-compose-env.yml down'
		// 		sh 'docker-compose -f env-config/docker-compose-env.yml up -d'
		// 		echo 'Environment Settings End'
		// 	}
		// }
		//===================================== Front app =====================================
		stage('build-front') {
			when {
				changeset "frontend/**/*"
			}
			steps {
				echo 'Build Start Front App'
				sh 'docker build -t front-vue-img frontend/. --no-cache'
				echo 'Build End Front App'
			}
		}
		
		// stage('deploy-front') {
		// 	when {
		// 		changeset "frontend/**/*"
		// 	}
		// 	steps {
		// 		echo 'Deploy Start Front App'
		// 		sh '''
		// 			if (docker ps | grep "front-vue"); then docker stop front-vue;
		// 			fi
		// 			docker run -it -d --rm -p 3000:3000 --name front-vue --network env-config_ae-book_network front-vue-img
		// 		'''
		// 		echo 'Deploy End Front App'
		// 	}
		// }
		//===================================== Spring API =====================================
		stage('build-spring-api') {
			when {
				anyOf {
					changeset "backend/**/*"
				}
			}
			steps {
				echo 'Build Start backend spring'
				sh '''
					chmod +x backend/gradlew
					backend/gradlew -p backend cleanQuerydslSourceDir		
					backend/gradlew -p backend build -x test
					docker build -t back-spring-img backend/. --no-cache
				'''
				echo 'Build End backend spring'
			}
		}
		// stage('deploy-spring-api') {
		// 	when {
		// 		anyOf {
		// 			changeset "backend/**/*"
		// 		}
		// 	}
		// 	steps {
		// 		echo 'Deploy Start "${APP_SPRING_API}"'
		// 		sh '''
		// 			if (docker ps | grep "back-spring"); then docker stop back-spring;
		// 			fi
		// 			docker run -it -d --rm -p 8082:8082 --name back-spring --network env-config_ae-book_network back-spring-img
		// 			'''
		// 		echo 'Deploy End "${APP_SPRING_API}"'
		// 	}
		// }
		//===================================== Batch =====================================
		stage('build-batch') {
			when {
				anyOf {
					changeset "batch/**/*"
				}
			}
			steps {
				echo 'Build Start batch'
				sh '''
				    chmod +x batch/gradlew
					batch/gradlew -p batch build -x test
					docker build -t back-batch-img batch/. --no-cache
				'''
				echo 'Build End batch'
			}
		}
		// batch는 build 될 때마다 데이터가 들어가기 때문에 변경사항있다고 run하는건 아닌 듯...
		// stage('deploy-batch-api') {
		// 	when {
		// 		anyOf {
		// 			changeset "batch/**/*"
		// 		}
		// 	}
		// 	steps {
		// 		echo 'Deploy Start "${APP_BATCH}"'
		// 		sh '''
		// 			if (docker ps | grep "back-batch"); then docker stop back-batch;
		// 			fi
		// 			docker run -it -d --rm -p 8084:8082 --name back-batch --network env-config_ae-book_network back-batch-img
		// 			'''
		// 		echo 'Deploy End "${APP_BATCH}"'
		// 	}
		// }
		//===================================== AI =====================================
		stage('build-ai-api') {
			when {
				anyOf {
					changeset "ai/**/*"
				}
			}
			steps {
				echo 'Build Start backend fastAPI'
				sh '''
					docker build -t back-fast-img ai/. --no-cache
				'''
				echo 'Build End backend fastAPI'
			}
		}
		// stage('deploy-ai-api') {
		// 	when {
		// 		anyOf {
		// 			changeset "ai/**/*"
		// 		}
		// 	}
		// 	steps {
		// 		echo 'Deploy Start "${APP_SPRING_API}"'
		// 		sh '''
		// 			if (docker ps | grep "back-fast"); then docker stop back-fast;
		// 			fi
		// 			docker run -it -d --rm -p 8000:8000 --name back-fast --network env-config_ae-book_network back-fast-img
		// 			'''
		// 		echo 'Deploy End "${APP_SPRING_API}"'
		// 	}
		// }
	}
}
